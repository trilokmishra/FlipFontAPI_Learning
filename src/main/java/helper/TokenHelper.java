package helper;

import api.apps.POSTAuthenticateTaskManager;
import com.monotype.api_utils.logger.extentreportlogger.LogStatus;
import com.monotype.api_utils.logger.extentreportlogger.MTLogger;
import com.monotype.api_utils.settings.PropertiesManager;
import pojo.AuthenticateResponseBody;

/**
 * Created by kohlih on 26-06-2017.
 */
public class TokenHelper {

    private static String token="";

    public static String getToken(MTLogger mtLogger) throws Exception {

        //If token exists, then return existing one else create a new one by calling the POST Authenticate API
        if(token=="")
        {

            POSTAuthenticateTaskManager postAuthenticateTaskManager = new POSTAuthenticateTaskManager(PropertiesManager.getProperty("BaseURI"),mtLogger);
            try
            {
                //Call Perform for the POST Authenticate API
                String appID = PropertiesManager.getProperty("app_id");
                String hash = MD5Helper.createMD5(PropertiesManager.getProperty("app_id") + "." + PropertiesManager.getProperty("app_key"));
                postAuthenticateTaskManager.perform(PropertiesManager.getProperty("BaseVersion"),appID,hash);

                //Get the Token from the API Response
                AuthenticateResponseBody response=postAuthenticateTaskManager.getRestDriver().getAPIResponseAsPOJO(AuthenticateResponseBody.class);
                token = response.getToken();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                mtLogger.log(LogStatus.FATAL,"Not able to get Session ID. Error Message - " + e.getMessage());
            }
        }
        return token;
    }
}