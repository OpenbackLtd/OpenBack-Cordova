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

import com.openback.android.sdk.utils.helper.CreateUser;
import com.openback.android.sdk.utils.helper.InitializeOpenBack;
import com.openback.android.sdk.utils.helper.CustomTriggerHelper;
import com.openback.android.sdk.utils.models.UserInfoExtraDTO;

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
			JSONObject userInfo = args.getJSONObject(0);
			if (userInfo == null) {
				callbackContext.error("Invalid user info");
				return;
			}

			String gcmSenderId = this.preferences.getString("com.openback.gcmSenderId", "");
			InitializeOpenBack.setGcmSenderId(this.getApplicationContext(), gcmSenderId);

			UserInfoExtraDTO userInfoExtra = new UserInfoExtraDTO();
			if (userInfo.has("addressLine1")) {
				userInfoExtra.setAddressLine1(userInfo.getString("addressLine1"));
			}
			if (userInfo.has("addressLine2")) {
				userInfoExtra.setAddressLine2(userInfo.getString("addressLine2"));
			}
			if (userInfo.has("advertisingId")) {
				userInfoExtra.setAdvertisingId(userInfo.getString("advertisingId"));
			}
			if (userInfo.has("age")) {
				userInfoExtra.setAge(userInfo.getString("age"));
			}
			if (userInfo.has("city")) {
				userInfoExtra.setCity(userInfo.getString("city"));
			}
			if (userInfo.has("country")) {
				userInfoExtra.setCountry(userInfo.getString("country"));
			}
			if (userInfo.has("countryCode")) {
				userInfoExtra.setCountryCode(userInfo.getString("countryCode"));
			}
			if (userInfo.has("dateOfBirth")) {
				userInfoExtra.setDateOfBirth(userInfo.getString("dateOfBirth"));
			}
			if (userInfo.has("emailAddress")) {
				InitializeOpenBack.setUserEmailAddress(this.getApplicationContext(), userInfo.getString("emailAddress"));
			}
			if (userInfo.has("firstName")) {
				userInfoExtra.setFirstName(userInfo.getString("firstName"));
			}
			if (userInfo.has("gender")) {
				userInfoExtra.setGender(userInfo.getString("gender"));
			}
			if (userInfo.has("phoneNumber")) {
				InitializeOpenBack.setUserMsisdn(this.getApplicationContext(), userInfo.getString("phoneNumber"));
			}
			if (userInfo.has("postCode")) {
				userInfoExtra.setPostCode(userInfo.getString("postCode"));
			}
			if (userInfo.has("profession")) {
				userInfoExtra.setProfession(userInfo.getString("profession"));
			}
			if (userInfo.has("state")) {
				userInfoExtra.setState(userInfo.getString("state"));
			}
			if (userInfo.has("surname")) {
				userInfoExtra.setSurname(userInfo.getString("surname"));
			}
			if (userInfo.has("title")) {
				userInfoExtra.setTitle(userInfo.getString("title"));
			}

			String applicationId = this.preferences.getString("com.openback.applicationId", "");
			InitializeOpenBack.createNewUserCreationRequest(this.getApplicationContext(), applicationId, userInfoExtra);
			callbackContext.success();
			// TODO: InitializeOpenBack.setAppUserId(getApplicationContext(),"AppUserId");
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
				CustomTriggerHelper.setCustomIdValue(this.getApplicationContext(), trigger, value.toString(), "Integer");
				callbackContext.success();
			} else if (value.getClass().equals(Double.class)) {
				CustomTriggerHelper.setCustomIdValue(this.getApplicationContext(), trigger, value.toString(), "Double");
				callbackContext.success();
			} else if (value.getClass().equals(String.class)) {
				CustomTriggerHelper.setCustomIdValue(this.getApplicationContext(), trigger, value.toString(), "String");
				callbackContext.success();
			} else {
				callbackContext.error(value.getClass() + " is not supported");
			}
		} catch (Exception e) {
			callbackContext.error(e.toString());
		}
	}
}