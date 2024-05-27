package org.example.learn;

import ViewModel.OpenImageVM;

public class OpenImage {
    private ViewHandler viewHandler;
    private OpenImageVM openVM;
    public void init(OpenImageVM openVM, ViewHandler viewHandler) {
        this.openVM = openVM;
        this.viewHandler = viewHandler;
    }
}
