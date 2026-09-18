pragma solidity 0.8.13;
//SPDX-License-Identifier: MIT

/// @title Jaccard Similarity Algorithm Tryout for goal similarity
/// @author Orcun Oruc
/// @notice You can use this algorithm to detect similarities
/// @dev similarity of two different strings aspect of goals. - If we converted strings from the Goal, Plan, Belief and Intention definitions from Java code, we can use this to compares
/// @dev current we assume that the converted string should be exactly same in the abstracted definition such as class name, interface name, enumeration name or field name.
/// @custom:experimental It is not necessary because we are going to provide similarity with classes, interfaces and abstraction levels
/// @custom:experimental Probably, we are not going to use this.
contract JaccardSimilarity {
    uint256 public result;
    // Using a temporary mapping to count unique characters
    mapping(bytes1 => bool) seenInStr1;
    mapping(bytes1 => bool) seenInStr2;


/// @dev Calculates the Jaccard Similarity between two byte arrays
    /// @param str1 First byte array
    /// @param str2 Second byte array
    /// @return similarityLevel Jaccard similarity level as a uint256 fixed-point with 18 decimals
    function calculateJaccardSimilarity(bytes memory str1, bytes memory str2) private returns (uint256 similarityLevel) {
        uint256 intersection;
        uint256 union;


        // Count intersection and union
        for (uint256 i = 0; i < str1.length; i++) {
            seenInStr1[str1[i]] = true; // Mark character as seen in str1
        }

        for (uint256 i = 0; i < str2.length; i++) {
            if (seenInStr1[str2[i]]) {
                intersection++;
                delete seenInStr1[str2[i]]; // Avoid counting duplicates
            }
            seenInStr2[str2[i]] = true; // Mark character as seen in str2
        }

        // Count union (all unique characters in both strings)
        for (uint256 i = 0; i < str1.length; i++) {
            if (seenInStr1[str1[i]]) {
                union++;
                delete seenInStr1[str1[i]]; // Avoid counting duplicates
            }
        }

        for (uint256 i = 0; i < str2.length; i++) {
            if (seenInStr2[str2[i]]) {
                union++;
                delete seenInStr2[str2[i]]; // Avoid counting duplicates
            }
        }

        if (union == 0) {
            return 0; // Avoid division by zero
        }

        // Solidity does not support floating-point arithmetic, so we use fixed-point math with 18 decimal places
        // Similarity = Intersection / Union * (10^18)
        return (intersection * 10**18) / union;
    }

    /// @notice Executes the Jaccard Similarity calculation between two strings
    /// @param str1 First string
    /// @param str2 Second string
    /// @return Jaccard similarity as a uint256 fixed-point with 18 decimals
    function executeJaccardSimilarity(string memory str1, string memory str2) public returns (uint256) {
        bytes memory bytesStr1 = bytes(str1);
        bytes memory bytesStr2 = bytes(str2);
        result = calculateJaccardSimilarity(bytesStr1, bytesStr2);
        return result;
    }
}



