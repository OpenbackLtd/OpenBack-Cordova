package com.openback.cordova;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import com.openback.OpenBack;
import com.openback.UserInfoExtra;

public class OpenBackPlugin extends CordovaPlugin {

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		
		try {		
			Context context = this.getApplicationContext();
			OpenBack.Config config = new OpenBack.Config(context);

			String gcmSenderId = this.preferences.getString("com.openback.gcmSenderId", "");
			if (gcmSenderId != null && gcmSenderId.length() > 0) {
				config.setGcmSenderId(gcmSenderId);
			}

			String appCode = this.preferences.getString("com.openback.appCode", "");
			if (appCode != null && appCode.length() > 0) {
				config.setOpenBackAppCode(appCode);
			}
			
			OpenBack.start(config);

		} catch (Exception e) {
			Log.e("OpenBack", "Oops", e);
		}
	}


	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		switch (action) {
			case "coppaCompliant":
				coppaCompliant(args, callbackContext);
				break;
			case "gdprForgetUser":
				gdprForgetUser(args, callbackContext);
				break;
			case "logGoal":
				logGoal(args, callbackContext);
				break;
			case "sdkVersion":
				sdkVersion(args, callbackContext);
				break;
			case "setUserInfo":
				setUserInfo(args, callbackContext);
				break;
			case "setCustomTrigger":
				setCustomTrigger(args, callbackContext);
				break;
			default:
				return false;			
		}
	    return true;
	}

	private Context getApplicationContext() {
		return this.cordova.getActivity().getApplicationContext();
	}

	private void coppaCompliant(JSONArray args, CallbackContext callbackContext) {
		try {
			Context context = this.getApplicationContext();
			boolean compliant = args.getBoolean(0);
			OpenBack.coppaCompliant(context, compliant);
			callbackContext.success();
		} catch (Exception e) {
			callbackContext.error(e.toString());
		}

	}

	private void gdprForgetUser(JSONArray args, CallbackContext callbackContext) {
		try {
			Context context = this.getApplicationContext();
			boolean forgetUser = args.getBoolean(0);
			OpenBack.gdprForgetUser(context, forgetUser);
			callbackContext.success();
		} catch (Exception e) {
			callbackContext.error(e.toString());
		}
	}

	private void logGoal(JSONArray args, CallbackContext callbackContext) {
		try {
			Context context = this.getApplicationContext();
			String goal = args.getString(0);
			int step = args.getInt(1);
			double value = args.getDouble(2);
			OpenBack.logGoal(context, goal, step, value);
			callbackContext.success();
		} catch (Exception e) {
			callbackContext.error(e.toString());
		}
	}

	private void sdkVersion(JSONArray args, CallbackContext callbackContext) {
		try {
			callbackContext.success(OpenBack.getSdkVersion());
		} catch (Exception e) {
			callbackContext.error(e.toString());
		}
	}

	private void setUserInfo(JSONArray args, CallbackContext callbackContext) {
		try {
			Context context = this.getApplicationContext();

			JSONObject userInfo = args.getJSONObject(0);
			if (userInfo == null) {
				callbackContext.error("Invalid user info");
				return;
			}

            // User Extra Info
            UserInfoExtra userInfoExtra = new UserInfoExtra();
            
			if (userInfo.has("addressLine1")) {
				userInfoExtra.AddressLine1 = userInfo.getString("addressLine1");
			}
			if (userInfo.has("addressLine2")) {
				userInfoExtra.AddressLine2 = userInfo.getString("addressLine2");
			}
			if (userInfo.has("advertisingId")) {
				userInfoExtra.AdvertisingId = userInfo.getString("advertisingId");
			}
			if (userInfo.has("age")) {
				userInfoExtra.Age = userInfo.getString("age");
			}
			if (userInfo.has("city")) {
				userInfoExtra.City = userInfo.getString("city");
			}
			if (userInfo.has("country")) {
				userInfoExtra.Country = userInfo.getString("country");
			}
			if (userInfo.has("countryCode")) {
				userInfoExtra.CountryCode  = userInfo.getString("countryCode");
			}
			if (userInfo.has("dateOfBirth")) {
				userInfoExtra.DateOfBirth = userInfo.getString("dateOfBirth");
			}
			if (userInfo.has("firstName")) {
				userInfoExtra.FirstName = userInfo.getString("firstName");
			}
			if (userInfo.has("gender")) {
				userInfoExtra.Gender = userInfo.getString("gender");
			}
			if (userInfo.has("postCode")) {
				userInfoExtra.PostCode = userInfo.getString("postCode");
			}
			if (userInfo.has("profession")) {
				userInfoExtra.Profession = userInfo.getString("profession");
			}
			if (userInfo.has("state")) {
				userInfoExtra.State = userInfo.getString("state");
			}
			if (userInfo.has("surname")) {
				userInfoExtra.Surname = userInfo.getString("surname");
			}
			if (userInfo.has("title")) {
				userInfoExtra.Title = userInfo.getString("title");
			}
			if (userInfo.has("identity1")) {
				userInfoExtra.Identity1 = userInfo.getString("identity1");
			}
			if (userInfo.has("identity2")) {
				userInfoExtra.Identity2 = userInfo.getString("identity2");
			}
			if (userInfo.has("identity3")) {
				userInfoExtra.Identity3 = userInfo.getString("identity3");
			}
			if (userInfo.has("identity4")) {
				userInfoExtra.Identity4 = userInfo.getString("identity4");
			}
			if (userInfo.has("identity5")) {
				userInfoExtra.Identity5 = userInfo.getString("identity5");
			}

			String email = "";
			if (userInfo.has("emailAddress")) {
				email = userInfo.getString("emailAddress");
			}
			String phoneNumber = "";
			if (userInfo.has("phoneNumber")) {
				phoneNumber = userInfo.getString("phoneNumber");
			}

			OpenBack.update(new OpenBack.Config(context)
                    .setExtraUserInfo(userInfoExtra)
                    .setUserEmail(email)
                    .setUserMsisdn(phoneNumber));			
			
			callbackContext.success();
		} catch (Exception e) {
			callbackContext.error(e.toString());
		}
	}

	private void setCustomTrigger(JSONArray args, CallbackContext callbackContext) {
		try {
			Object value = args.get(1);
			int trigger = args.getInt(0) + 1; // Zero based in iOS
			if (value == null) {
				callbackContext.error("Null value is not supported");
			} else if (value.getClass().equals(Integer.class)) {
				OpenBack.setCustomTrigger(this.getApplicationContext(), trigger, (Integer)value);
				callbackContext.success();
			} else if (value.getClass().equals(Long.class)) {
				OpenBack.setCustomTrigger(this.getApplicationContext(), trigger, (Long)value);
				callbackContext.success();
			} else if (value.getClass().equals(Float.class)) {
				OpenBack.setCustomTrigger(this.getApplicationContext(), trigger, (Float)value);
				callbackContext.success();
			} else if (value.getClass().equals(Double.class)) {
				OpenBack.setCustomTrigger(this.getApplicationContext(), trigger, (Float)value);
				callbackContext.success();
			} else if (value.getClass().equals(String.class)) {
				OpenBack.setCustomTrigger(this.getApplicationContext(), trigger, (String)value);
				callbackContext.success();
			} else {
				callbackContext.error(value.getClass() + " is not supported");
			}				
		} catch (Exception e) {
			callbackContext.error(e.toString());
		}
	}
}
