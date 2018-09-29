package com.example.peterknut.maintainservice;



import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.core.ILiveLoginManager;

/**
 * Created by valexhuang on 2018/4/4.
 */

public class LoginHelper implements ILiveLoginManager.TILVBStatusListener {

    private ILoginView loginView;
    private static boolean mLoginState;
    private static String mCurrentAccount;

    public LoginHelper(ILoginView loginView) {
        this.loginView = loginView;
    }


    public void login(final String account) {
        if (isLogin()) {
            if (account.equals(mCurrentAccount)) {
                ILiveLoginManager.getInstance().iLiveLogout(new ILiveCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        mLoginState = false;
                        mCurrentAccount = null;
                        loginView.onLogoutSDKSuccess();
                        StatusObservable.getInstance().deleteObserver(LoginHelper.this);
                    }

                    @Override
                    public void onError(String module, int errCode, String errMsg) {
                        loginView.onLogoutSDKFailed(module, errCode, errMsg);
                    }
                });
            } else {
                UIUtils.toastLongMessage("请先注销" + mCurrentAccount);
            }
        } else {
            /**
             *
             * 此处请自行参考文档注册用户account并生成userSig进行测试
             * 文档地址：https://gitee.com/vqcloud/doc_demo/blob/master/%E5%BC%80%E5%8F%91%E5%89%8D%E5%BF%85%E7%9C%8B/%E5%9F%BA%E6%9C%AC%E6%A6%82%E5%BF%B5.md#%E7%94%A8%E6%88%B7%E7%AD%BE%E5%90%8D
             *
             */
            ILiveLoginManager.getInstance().iLiveLogin(account, "eJxNj0FvgkAQhf9Ks*em2WVBjYkHQGhsSyy1BdzLhrArTKiAsBSJ6X8vEjSd23wvb*a9C-p82z3FSVK2heKqryRaIoweRwxCFgoOIOsBpq1sFNHopMVVBYLHitNa-LM0IuejNDCiY0x0neCbR54rqCWPD2q8SPHdBemwe45vb5zNrmv29kmUfvlypF-tNoz224BpVd2X0ess-UjegyxnC5aZYHX9OlyTLuosy8g9l2T2nNnP3w74jQijeXCeeezkmm4C5mo1PVNwvJYkBl1oVB8STvxH1g2UBVo*IA0TY6iKr4N*-wC*CFfv", new ILiveCallBack() {
                @Override
                public void onSuccess(Object data) {
                    mLoginState = true;
                    loginView.onLoginSDKSuccess();
                    StatusObservable.getInstance().addObserver(LoginHelper.this);
                    ILiveLoginManager.getInstance().setUserStatusListener(StatusObservable.getInstance());
                    mCurrentAccount = account;
                }

                @Override
                public void onError(String module, int errCode, String errMsg) {
                    mLoginState = false;
                    loginView.onLoginSDKFailed(module, errCode, errMsg);
                }
            });
        }


    }


    @Override
    public void onForceOffline(int error, String message) {
        mLoginState = false;
        mCurrentAccount = null;
        loginView.updateLoginState(false);
    }

    public static boolean isLogin() {
        return mLoginState;
    }

    public static String getCurrentAccount() {
        return mCurrentAccount;
    }


}
