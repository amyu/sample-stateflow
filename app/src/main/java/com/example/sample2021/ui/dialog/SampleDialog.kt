package com.example.sample2021.ui.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.example.sample2021.R
import com.example.sample2021.databinding.DialogSampleBinding


class SampleDialog : DialogFragment(R.layout.dialog_sample) {
    companion object {
        private const val TAG = "SampleDialog"
        private const val REQUEST_KEY = "SampleDialog_REQUEST_KEY"

        private const val RESULT_KEY_CLOSE = "SampleDialog_RESULT_KEY_CLOSE"


        fun show(
            target: Fragment,
            fragmentManager: FragmentManager = target.childFragmentManager,
            tag: String = TAG,
            onDismiss: () -> Unit
        ) {
            newInstance().apply {

            }.run {
                fragmentManager.setFragmentResultListener(
                    REQUEST_KEY,
                    target.viewLifecycleOwner
                ) { requestKey, bundle ->
                    if (requestKey != REQUEST_KEY) {
                        return@setFragmentResultListener
                    }

                    when {
                        bundle.containsKey(RESULT_KEY_CLOSE) -> onDismiss()
                    }
                }
                val dialogFragment = fragmentManager.findFragmentByTag(TAG) as? DialogFragment
                if (dialogFragment?.dialog?.isShowing == true) {
                    return
                }
                showNow(fragmentManager, tag)
            }
        }

        private fun newInstance() = SampleDialog()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DialogSampleBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        //必要に応じてVMを作りましょう

        binding.closeButton.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        setFragmentResult(REQUEST_KEY, bundleOf(RESULT_KEY_CLOSE to ""))
        super.onDismiss(dialog)
    }
}