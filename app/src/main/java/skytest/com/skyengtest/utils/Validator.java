package skytest.com.skyengtest.utils;


import android.text.TextUtils;

public  class  Validator {
    public static boolean isEmailValid(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    public static boolean isPasValid(CharSequence target) {
        return target.length()>=4;
    }

    public static boolean isCodeValid(CharSequence target) {
        return target.length()>=4;
    }
}
