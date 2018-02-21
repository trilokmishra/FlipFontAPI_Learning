package api.apps;

import com.monotype.api_utils.core.TaskManager;
import com.monotype.api_utils.logger.extentreportlogger.MTLogger;
import com.monotype.api_utils.settings.PropertiesManager;
import helper.MD5Helper;

import java.util.Map;

/**
 * Created by kohlih on 27-06-2017.
 */
public class POSTAuthenticateTaskManager extends TaskManager<POSTAuthenticateTaskManager> {
    POSTAuthenticateCreateRequestTask createRequestTask;
    POSTAuthenticateExecuteRequestTask executeRequestTask;
    POSTAuthenticateValidateResponseTask validateResponseTask;
    POSTAuthenticateValidateNegativeResponseTask validateNegativeResponseTask;

    String baseURI;
    MTLogger mtLogger;

    public POSTAuthenticateTaskManager(String baseURI,MTLogger mtLogger) {
        this.baseURI = baseURI;
        this.mtLogger = mtLogger;
    }

    public String perform(String version,String appID,String hash)
    {
        createRequestTask = new POSTAuthenticateCreateRequestTask(baseURI,mtLogger,version,appID,hash);
        executeRequestTask = new POSTAuthenticateExecuteRequestTask(mtLogger);
        validateResponseTask = new POSTAuthenticateValidateResponseTask(mtLogger);
        addTask(createRequestTask);
        addTask(executeRequestTask);
        addTask(validateResponseTask);
        execute();
        return getStatus();
    }

    public String performNegative(Map<String, String> testData)
    {
        String version;
        String appID;
        String appKey;
        if(testData.get("version").equals("fromtestsettings"))
        {
            version = PropertiesManager.getProperty("BaseVersion");
        }
        else
        {
            version = testData.get("version");
        }
        if(testData.get("appID").equals("fromtestsettings"))
        {
            appID = PropertiesManager.getProperty("app_id");
        }
        else
        {
            appID = testData.get("appID");
        }
        if(testData.get("appKey").equals("fromtestsettings"))
        {
            appKey = PropertiesManager.getProperty("app_key");
        }
        else
        {
            appKey = testData.get("appKey");
        }

        String hash = MD5Helper.createMD5(appID + "." + appKey);

        createRequestTask = new POSTAuthenticateCreateRequestTask(baseURI,mtLogger,version,appID,hash);
        executeRequestTask = new POSTAuthenticateExecuteRequestTask(mtLogger);
        validateNegativeResponseTask = new POSTAuthenticateValidateNegativeResponseTask(mtLogger,testData.get("statusCode"));
        addTask(createRequestTask);
        addTask(executeRequestTask);
        addTask(validateNegativeResponseTask);
        execute();
        return getStatus();
    }
}