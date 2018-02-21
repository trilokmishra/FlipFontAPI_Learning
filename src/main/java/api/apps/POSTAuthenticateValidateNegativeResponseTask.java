package api.apps;

import com.monotype.api_utils.core.Task;
import com.monotype.api_utils.core.TaskManager;
import com.monotype.api_utils.logger.extentreportlogger.MTLogger;

import java.util.Map;

/**
 * Created by kohlih on 27-06-2017.
 */
public class POSTAuthenticateValidateNegativeResponseTask extends Task<POSTAuthenticateTaskManager> {

    MTLogger objMTLogger;
    String statusCode;

    public POSTAuthenticateValidateNegativeResponseTask(MTLogger objMTLogger,String statusCode) {
        this.objMTLogger = objMTLogger;
        this.statusCode = statusCode;
    }

    @Override
    public void perform(TaskManager<POSTAuthenticateTaskManager> taskManager) {
        startTimer();
        taskManager.getRestDriver().getobjMTResponseBuilder().setStatusCode(Integer.parseInt(statusCode));
        taskManager.getRestDriver().createExpectedResponse(taskManager.getRestDriver().getobjMTResponseBuilder());
        taskManager.getRestDriver().validateResponse();
        setTaskSuccessfull();
        endTimer();
    }
}