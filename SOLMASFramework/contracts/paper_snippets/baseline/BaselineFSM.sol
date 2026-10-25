// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/// Minimal hand-written finite-state machine used as a baseline for the
/// SocialRbSC role behaviour contract. It models the same delivery lifecycle
/// (pending -> accepted -> in transit -> delivered) without role checks,
/// motivational guards, or framework modifiers.
contract BaselineFSM {

    enum State { PENDING, ACCEPTED, IN_TRANSIT, DELIVERED }

    State public state;

    event StateChanged(State s);

    function accept() external {
        require(state == State.PENDING, "invalid transition");
        state = State.ACCEPTED;
        emit StateChanged(state);
    }

    function ship() external {
        require(state == State.ACCEPTED, "invalid transition");
        state = State.IN_TRANSIT;
        emit StateChanged(state);
    }

    function deliver() external {
        require(state == State.IN_TRANSIT, "invalid transition");
        state = State.DELIVERED;
        emit StateChanged(state);
    }
}
