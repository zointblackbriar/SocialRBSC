// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;



// They are motivational components of the agent's decision-making process
// @dev Desires (Goals the agent wants to achieve)
contract SocialAgentDesire {
    constructor(){
        desireStatus = Desire.None;
    }

//    mapping(string => Desire) desireMapping;

    // @dev Current intention
    enum Desire {None, ExecuteIntention}
    Desire public desireStatus;

    // @dev Function to get the current desire
    function getDesire() public view returns(string memory){
        if (desireStatus == Desire.None) {
            return "None";
        } else if (desireStatus == Desire.ExecuteIntention) {
            return "ExecuteIntention";
        } else {
            return "Unknown";
        }
    }


    // @dev Function to map with metamodeled desire with status of desire
    function setDesire(string memory _metamodeledDesire, Desire _statusOfDesire) public {
        desireStatus = _statusOfDesire;
    }
}



