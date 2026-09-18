### INFORMATION

Agents should have plans and plans can create such situations as follows: 

```bash
public void body() {
    //Plan code here
}

public void passed() {
    //Optional cleanup code in case of a plan success
}

public void failure() {
    //Optional cleanup code in case of a plan failure
}

public void aborted() {
    //Optional cleanup code in case the plan is aborted.
}

```

### AOP-based Information

## Insights regarding the Deterministic On-chain Agent Framework

1. observe the world and the agent’s internal state, and
update the event queue consequently;
2. generate possible new plan instances whose trigger event
matches an event in the event queue (relevant plan instances)
and whose precondition is satisfied (applicable plan instances);
3. select for execution one instance from the set of appli-
cable plan instances;
4. push the selected instance onto an existing or new
intention stack, according to whether or not the event is a
(sub)goal;

***In the BDI Interpreter***,
we need to have a event-based framework in multi-agent systems. This concept could be important for deterministic 
agent frameworks. Agents are going to make decisions by processing events. Events are processed in the order they arrive.
Processing of events involves matching. 

Once an event is selected, an agent must execute it. This is done by adopting a new intention or refining an existing intention. In the case where the event is a belief event or an initial goal event, a new intention is created. In the cases where the event is a subgoal event, the plan is added to the existing intention. At any point in time, an agent can have multiple intentions. Intentions are executed in parallel. On each iteration of the agent interpreter, a single intention is selected (if one exists) and the next step of the intention is executed (ASTRA BDI (Strongly it relies on AgentSpeak) approach).

Declarative goals declare a belief state you want to acheive. If we use the light example from the beliefs section, then the goal to turn the light on can be declared declaratively as !light("on") or non-declaratively as !turnLight("on") (there are other possible forms for the non-declarative version - !foo("on") would also be a valid non-declarative goal).

## Insights regarding the BDI Interpreter Logic of Agentspeak

Reasoning Cycle of the Agentspeak

1. Perceiving the Environment 
2. Updating the Belief Base
3. Receiving Communication from Other Agents
4. Selecting 'Socially Acceptable' Messages
5. Selecting an Event
6. Retrieving all Relevant Plans
7. Determining the Applicable Plans 
8. Selecting one Applicable Plan
9. Selecting an Intention for Further Execution
10. Executing one step of an Intention