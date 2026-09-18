// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;
import "./Proxiable.sol";

contract DummyProxiable is Proxiable{
    uint public value;

    function setValue(uint _value) public {
        value = _value;
    }
}



