/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.socialagentengine;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import de.tudresden.multiagentsystem.util.BlockchainUtil;
import static junit.framework.TestCase.assertEquals;


/**
 * Goal Plan Tree Test.
 *
 * @author $author$
 */
public class GoalPlanTreeIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * web3j object.
     */
    private static Web3j web3j;

    /**
     * TODO DOCUMENT ME!
     */
    private static Credentials credentials;

    /**
     * TODO DOCUMENT ME!
     */
    private static de.tudresden.codegenerator.autogen.GoalPlanTree goalPlanTree;

    /**
     * TODO DOCUMENT ME!
     */
    private static de.tudresden.codegenerator.autogen.Utils utils;

    /**
     * TODO DOCUMENT ME!
     */
    private static de.tudresden.codegenerator.autogen.LinkedList linkedList;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    // Encode the inputs and hash them using keccak256
    /**
     * TODO DOCUMENT ME!
     *
     * @param  parent TODO DOCUMENT ME!
     * @param  plan   TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static String keccak256EncodePackedString(String parent, String plan) {
        // Concatenate the strings like Solidity's abi.encodePacked
        String combinedInput = parent + plan;

        // Convert the concatenated string to bytes and compute keccak256
        byte[] inputBytes = combinedInput.getBytes(StandardCharsets.UTF_8);
        byte[] hashed = Hash.sha3(inputBytes);

        // Convert the hashed byte array to a hexadecimal string
        return Numeric.toHexString(hashed);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        // Connect to a local Ethereum node (e.g., Ganache, Infura)
        web3j = Web3j.build(new HttpService("http://localhost:8545"));

        // get the transaction manager
        TransactionManager transactionManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());

        // get gas provider
        DefaultGasProvider gasProvider = new DefaultGasProvider();

        goalPlanTree = de.tudresden.codegenerator.autogen.GoalPlanTree
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();

        TransactionReceipt receipt = goalPlanTree
                    .add("nodeName", "parentName", "data", BigInteger.valueOf(1), BigInteger.valueOf(5))
                    .send();

        linkedList = de.tudresden.codegenerator.autogen.LinkedList
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();

        utils = de.tudresden.codegenerator.autogen.Utils
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testConfigureContracts() {
        assertNotNull(goalPlanTree.getContractAddress());
        assertNotNull(linkedList.getContractAddress());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testCreatePlanLinkedToGoal() throws Exception {
        String goalName = "Goal 1";
        String planName = "Plan 1";
        String parent = "sampleParent";
        String goalData = "Data for Goal 1";
        String planData = "Data for Plan 1";
        BigInteger avoidance = BigInteger.valueOf(10);
        BigInteger willingness = BigInteger.valueOf(20);

        // Add a goal and a plan
        goalPlanTree.add(goalName, parent, goalData, avoidance, willingness).send();
        goalPlanTree.addPlan(planName, parent, planData, avoidance, willingness).send();

        byte[] goalPathEncodedData = utils.computeBytePath(parent, planName).send();
        System.out.println("goalPathEncodedData: " + goalPathEncodedData);
        // Convert back to hex string if necessary
        // String goalPathHexString = Numeric.toHexString(goalPath);

        Tuple6<String, String, String, String, String, List<byte[]>> goalPlanTreeTuple = goalPlanTree
                    .getNode(goalPathEncodedData)
                    .send();

        // Unpack the tuple to get individual fields
        String nameTuple = goalPlanTreeTuple.getValue1();
        String parentTuple = goalPlanTreeTuple.getValue2();
        String dataTuple = goalPlanTreeTuple.getValue3();
        String avoidanceParametersAddressTuple = goalPlanTreeTuple.getValue4();
        String willingnessParametersAddressTuple = goalPlanTreeTuple.getValue5();
        List<byte[]> childNodes = goalPlanTreeTuple.getValue6();

        System.out.println("nameTuple: " + nameTuple);
        System.out.println("parentTuple: " + parentTuple);
        System.out.println("dataTuple: " + dataTuple);
        System.out.println("Avoidance Parameters Address: " + avoidanceParametersAddressTuple);
        System.out.println("Willingness Parameters Address: " + willingnessParametersAddressTuple);
        System.out.println("Number of Child Nodes: " + childNodes.size());

        // // Verify that the goal node has the correct child nodes
        assertEquals(0, childNodes.size()); // should be one

        // String planPath = Hash.sha3String(parent + planName); // you don't need to use java function for this. All the time
        // blockchain method invocation should be used
        byte[] planPath = utils.computeBytePath(parent, planName).send();
        Tuple6 planNode = goalPlanTree.getNode(planPath).send();

        assertEquals(planName, planNode.getValue1());
        assertEquals(parent, planNode.getValue2());
        assertEquals(planData, planNode.getValue3());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testLinkPlanToGoal() throws Exception {
        // Add a goal
        String goalName = "Goal 1";
        String goalData = "Data for Goal 1";
        BigInteger goalAvoidance = BigInteger.valueOf(10);
        BigInteger goalWillingness = BigInteger.valueOf(15);
        goalPlanTree.add(goalName, "", goalData, goalAvoidance, goalWillingness).send();

        // Add a plan
        String planName = "Plan 1";
        String planData = "Data for Plan 1";
        BigInteger planAvoidance = BigInteger.valueOf(5);
        BigInteger planWillingness = BigInteger.valueOf(20);
        goalPlanTree.add(planName, "", planData, planAvoidance, planWillingness).send();

        // Link the plan to the goal
        goalPlanTree.linkPlanToGoal(planName, goalName).send();

        // Calculate the keccak256 hash of the goal name (as in Solidity)
        // byte[] goalPathBytes = Hash.sha3((goalName).getBytes());
        byte[] goalPathBytes = utils.computeBytePath("", goalName).send();

        // Call getNode with the correct 32-byte goalPath
        Tuple6<String, String, String, String, String, List<byte[]>> goalPlanTreeTuple = goalPlanTree
                    .getNode(goalPathBytes)
                    .send();

        // Unpack the tuple to get individual fields
        String nameTuple = goalPlanTreeTuple.component1();
        String parentTuple = goalPlanTreeTuple.component2();
        String dataTuple = goalPlanTreeTuple.component3();
        String avoidanceParametersAddressTuple = goalPlanTreeTuple.component4();
        String willingnessParametersAddressTuple = goalPlanTreeTuple.component5();
        List<byte[]> childNodes = goalPlanTreeTuple.component6();

        System.out.println("nameTuple: " + nameTuple);
        System.out.println("parentTuple: " + parentTuple);
        System.out.println("dataTuple: " + dataTuple);
        System.out.println("Avoidance Parameters Address: " + avoidanceParametersAddressTuple);
        System.out.println("Willingness Parameters Address: " + willingnessParametersAddressTuple);
        System.out.println("Number of Child Nodes: " + childNodes.size());

        // Ensure there is one child node
        assertEquals(1, childNodes.size()); // the plan is the next node of the goal - plan and goal are linked now

        // You can further check if the child node is the linked plan
        byte[] linkedPlanPath = childNodes.get(0);
        System.out.println("linkedPlanPath: " + linkedPlanPath);

        // Calculate the keccak256 hash of the plan name to compare
        // byte[] planPathBytes = Hash.sha3((planName).getBytes());
        byte[] planPathBytes = utils.computeBytePath("", planName).send();
        System.out.println("planPathBytes: " + planPathBytes);

        // Check if the linked plan matches the plan added
        assertArrayEquals(planPathBytes, linkedPlanPath);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testRemoveGoalAndSubgoals() throws Exception {
        // Add a parent goal
        String parentGoalName = "Parent Goal";
        String parentGoalData = "Parent goal data";
        BigInteger avoidance = BigInteger.valueOf(5);
        BigInteger willingness = BigInteger.valueOf(8);
        goalPlanTree.add(parentGoalName, "", parentGoalData, avoidance, willingness).send();

        // Add subgoals
        String subGoal1Name = "Sub Goal 1";
        String subGoal1Data = "Sub goal 1 data";
        goalPlanTree.add(subGoal1Name, parentGoalName, subGoal1Data, avoidance, willingness).send();

        String subGoal2Name = "Sub Goal 2";
        String subGoal2Data = "Sub goal 2 data";
        goalPlanTree.add(subGoal2Name, parentGoalName, subGoal2Data, avoidance, willingness).send();

        // Ensure goals and subgoals exist
        assertFalse(goalPlanTree.isDroppedGoal(parentGoalName, "").send());
        assertFalse(goalPlanTree.isDroppedGoal(subGoal1Name, parentGoalName).send());
        assertFalse(goalPlanTree.isDroppedGoal(subGoal2Name, parentGoalName).send());

        // Remove parent goal and subgoals
        // String parentGoalPath = Hash.sha3String("" + parentGoalName); // deprecated
        String parentGoalPath = keccak256EncodePackedString("", parentGoalName);

        // goalPlanTree.removeGoalAndSubgoals(GoalPlanTreeTest.toBytes32(parentGoalPath.getBytes())).send();
        goalPlanTree.removeGoalAndSubgoals(utils.stringToBytes32RobustVersion(parentGoalPath).send()).send();
    }

    /**
     * Helper function to convert byte array to a 32-byte array.
     *
     * @param  input TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    private static byte[] convertToBytes32(byte[] input) {
        // if the length of the byte is 32, return it directly
        if (input.length == 32) {
            return input;
        } else if (input.length > 32) {
            // If the byte array is longer than 32 bytes, truncate to the last 32 bytes
            return Arrays.copyOfRange(input, input.length - 32, input.length);
        } else {
            // If the byte array is shorter than 32 bytes, pad with leading zeros
            byte[] padded = new byte[32];
            System.arraycopy(input, 0, padded, 32 - input.length, input.length);

            return padded;
        }
    }
}
