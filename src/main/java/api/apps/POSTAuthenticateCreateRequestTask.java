package api.apps;

import com.monotype.api_utils.core.Task;
import com.monotype.api_utils.core.TaskManager;
import com.monotype.api_utils.core.requestexecutor.RequestType;
import com.monotype.api_utils.core.restman.MTRequestBuilder;
import com.monotype.api_utils.logger.extentreportlogger.MTLogger;
import com.monotype.api_utils.settings.PropertiesManager;
import helper.MD5Helper;
import pojo.AuthenticateRequestBody;

/**
 * Created by kohlih on 27-06-2017.
 */
public class POSTAuthenticateCreateRequestTask extends Task<POSTAuthenticateTaskManager> {

    String baseURI;
    String basePath="/apps/authenticate";
    MTLogger objMTLogger;
    String version;
    String appID;
    String hash;

    public POSTAuthenticateCreateRequestTask(String baseURI,MTLogger objMTLogger,String version,String appID,String hash) {
        this.baseURI = baseURI;
        this.objMTLogger = objMTLogger;
        this.version = version;
        this.appID = appID;
        this.hash = hash;
    }

    @Override
    public void perform(TaskManager<POSTAuthenticateTaskManager> taskManager) {
        startTimer();
        taskManager.getRestDriver().getobjMTRequestBuilder().setBaseURI(baseURI);
        taskManager.getRestDriver().getobjMTRequestBuilder().setBasePath(basePath);
        taskManager.getRestDriver().getobjMTRequestBuilder().setRequestType(RequestType.POST);
        taskManager.getRestDriver().getobjMTRequestBuilder().setContentType(MTRequestBuilder.RequestContentType.JSON);
        taskManager.getRestDriver().getobjMTRequestBuilder().setHeader("version",version);
        AuthenticateRequestBody authenticateRequestBody = new AuthenticateRequestBody();
        authenticateRequestBody.setAppId(appID);
        authenticateRequestBody.setHash(hash);
        taskManager.getRestDriver().getobjMTRequestBuilder().setReqBody(authenticateRequestBody);
        taskManager.getRestDriver().createRequest(taskManager.getRestDriver().getobjMTRequestBuilder());
        setTaskSuccessfull();
        endTimer();
    }
}