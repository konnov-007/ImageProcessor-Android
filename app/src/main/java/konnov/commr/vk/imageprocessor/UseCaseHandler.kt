package konnov.commr.vk.imageprocessor

/**
 * Runs [UseCase]s using a [UseCaseScheduler].
 */
class UseCaseHandler(private val mUseCaseScheduler: UseCaseScheduler) {

    fun <T : UseCase.RequestValues, R : UseCase.ResponseValue> execute(
        useCase: UseCase<T, R>, values: T, callback: UseCase.UseCaseCallback<R>
    ) {
        useCase.requestValues = values
        useCase.useCaseCallback = UiCallbackWrapper(callback, this)

        mUseCaseScheduler.execute(Runnable {
            useCase.run()
        })
    }

    fun <V : UseCase.ResponseValue> notifyResponse(
        response: V,
        useCaseCallback: UseCase.UseCaseCallback<V>
    ) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback)
    }

    private fun <V : UseCase.ResponseValue> notifyError(
        useCaseCallback: UseCase.UseCaseCallback<V>
    ) {
        mUseCaseScheduler.onError(useCaseCallback)
    }

    private class UiCallbackWrapper<V : UseCase.ResponseValue>(
        private val mCallback: UseCase.UseCaseCallback<V>,
        private val mUseCaseHandler: UseCaseHandler
    ) : UseCase.UseCaseCallback<V> {

        override fun onSuccess(response: V) {
            mUseCaseHandler.notifyResponse(response, mCallback)
        }

        override fun onError() {
            mUseCaseHandler.notifyError(mCallback)
        }
    }

    companion object {

        private var INSTANCE: UseCaseHandler? = null

        val instance: UseCaseHandler
            get() {
                if (INSTANCE == null) {
                    INSTANCE = UseCaseHandler(UseCaseThreadPoolScheduler())
                }
                return INSTANCE!!
            }
    }
}