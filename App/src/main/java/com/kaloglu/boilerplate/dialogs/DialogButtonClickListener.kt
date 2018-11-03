package com.kaloglu.boilerplate.dialogs


interface DialogListener {

    /**
     * Interface definition for a callback to be invoked when positive button is clicked.
     */
    interface OnButtonClickListener {
        /**
         * Called when positive button has been clicked.
         */
        fun onClick()
    }

}

