package org.ms.module.permission;


import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class PermissionUtils {
    private static final String TAG = PermissionUtils.class.getSimpleName();
    private PermissionFragment fragment;

    public PermissionUtils(@NonNull FragmentActivity activity) {
        this.fragment = this.getRxPermissionsFragment(activity);
    }

    private PermissionFragment getRxPermissionsFragment(FragmentActivity activity) {
        PermissionFragment fragment = (PermissionFragment) activity.getSupportFragmentManager()
                .findFragmentByTag(TAG);
        boolean isNewInstance = fragment == null;
        if (isNewInstance) {
            fragment = new PermissionFragment();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager.beginTransaction().add(fragment, TAG).commit();
            fragmentManager.executePendingTransactions();
        }
        return fragment;
    }

    public void requestPermissions(String[] permissions, PermissionListener listener) {
        this.fragment.setListener(listener);
        this.fragment.requestPermissions(permissions);
    }
}
