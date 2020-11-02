import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oracle.bpel.services.workflow.IWorkflowConstants;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.IWorkflowServiceClientConstants;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.repos.Predicate;
import oracle.bpel.services.workflow.repos.TableConstants;
import oracle.bpel.services.workflow.task.ITaskService;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.repos.Ordering;
import oracle.bpel.services.workflow.task.ITaskAssignee;
import oracle.bpel.services.workflow.task.impl.TaskAssignee;
import oracle.bpel.services.workflow.task.model.Task;


public class TaskReassingService {
    public static void main(String[] args) throws WorkflowException,StaleObjectException 
	{
        String domainUrl ="t3://localhost:8001"; // host:port of the soa server
        String username="pocuser1"; // user credentials
        String password="welcome1";//  user credentials
        IWorkflowServiceClient workflowServiceClient;
        Map<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String> connProperties = new HashMap<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String>();
        connProperties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.CLIENT_TYPE,WorkflowServiceClientFactory.REMOTE_CLIENT);
        connProperties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.EJB_PROVIDER_URL,domainUrl);
        connProperties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.EJB_INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        workflowServiceClient = WorkflowServiceClientFactory.getWorkflowServiceClient(connProperties, null, null);
      
        //Create ITaskQueryService reference 
        ITaskQueryService itaskQueryService= workflowServiceClient.getTaskQueryService();
           
        //Create IWorkflowContext reference 
        IWorkflowContext iworkFlowContext=itaskQueryService.authenticate(username, password.toCharArray(), null);
        
		//Predicate
        Predicate statePredicate=  new Predicate(TableConstants.WFTASK_STATE_COLUMN,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_ASSIGNED);
        
		//Desplay Column List
        List<String> queryColumns = new ArrayList<String>();
        queryColumns.add("TASKNUMBER");
        queryColumns.add("TASKID");
        queryColumns.add("TASKNUMBER");
        queryColumns.add("TITLE");
        queryColumns.add("OUTCOME");
        queryColumns.add("STATE");
        queryColumns.add("PRIORITY");
        
		//Optional Column
        List optionalInfo = new ArrayList();      
                optionalInfo.add("Comments");
                optionalInfo.add("Payload");   
        
       // Call query Task method
       List taskList = itaskQueryService.queryTasks(iworkFlowContext,
       queryColumns, //Custom Defined QueryColumns list
       optionalInfo, // Do not query additional info
       ITaskQueryService.AssignmentFilter.MY_AND_GROUP,
       null, //No keywords
       statePredicate, //Custom Defined Predicate
       null, // No Task Ordering ordering
       0,0);  // Do not page the query result
      
      
       System.out.println("Total Task found : "+taskList.size());
            System.out.println("taskNumber                   title                          state              taskId");
       

	   for (int i = 0; i < taskList.size(); i++) 
        {
            Task task = (Task)taskList.get(i);
            String taskId = task.getSystemAttributes().getTaskId();                
            String title = task.getTitle();
            String state = task.getSystemAttributes().getState();
            int taskNumber = task.getSystemAttributes().getTaskNumber();

            System.out.println(taskNumber+"                      "+title+"                       "+state+"     "+taskId);  
            
      }//end of for
        
}
}