package api.apps;

import com.monotype.api_utils.core.Task;
import com.monotype.api_utils.core.TaskManager;
import com.monotype.api_utils.logger.extentreportlogger.MTLogger;

/**
 * Created by kohlih on 27-06-2017.
 */
public class POSTAuthenticateValidateResponseTask extends Task<POSTAuthenticateTaskManager> {

    MTLogger objMTLogger;

    public POSTAuthenticateValidateResponseTask(MTLogger objMTLogger) {
        this.objMTLogger = objMTLogger;
    }
    @Override
    public void perform(TaskManager<POSTAuthenticateTaskManager> taskManager) {
        startTimer();
        taskManager.getRestDriver().getobjMTResponseBuilder().setStatusCode(200);
        taskManager.getRestDriver().createExpectedResponse(taskManager.getRestDriver().getobjMTResponseBuilder());
        taskManager.getRestDriver().validateResponse();
        setTaskSuccessfull();
        endTimer();
    }
}