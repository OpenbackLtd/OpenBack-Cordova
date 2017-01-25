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
	public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
		if (action.equals("setUserInfo")) {
			this.cordova.getThreadPool().execute(new Runnable() {
				@Override
				public void run() {
					setUserInfo(args, callbackContext);
				}
			});
			return true;
		} else if (action.equals("setValueForCustomTrigger")) {
			this.cordova.getThreadPool().execute(new Runnable() {
				@Override
				public void run() {
					setValueForCustomTrigger(args, callbackContext);
				}
			});
			return true;
		}
	    return false;
	}

	private Context getApplicationContext() {
		return this.cordova.getActivity().getApplicationContext();
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

			String gcmSenderId = this.preferences.getString("com.openback.gcmSenderId", "");
			String appCode = this.preferences.getString("com.openback.appCode", "");
			String email = "";
			if (userInfo.has("emailAddress")) {
				email = userInfo.getString("emailAddress");
			}
			String phoneNumber = "";
			if (userInfo.has("phoneNumber")) {
				phoneNumber = userInfo.getString("phoneNumber");
			}			

			// Initialize OpenBack
            OpenBack.start(new OpenBack.Config(context)
                    .setOpenBackAppCode(appCode)
                    .setExtraUserInfo(userInfoExtra)
                    .setUserEmail(email)
                    .setUserMsisdn(phoneNumber)
                    .setGcmSenderId(gcmSenderId));
			
			callbackContext.success();
		} catch (Exception e) {
			callbackContext.error(e.toString());
		}
	}

	private void setValueForCustomTrigger(JSONArray args, CallbackContext callbackContext) {
		try {
			Object value = args.get(0);
			int trigger = args.getInt(1) + 1; // Zero based in iOS
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
