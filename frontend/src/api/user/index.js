import axios from "axios";
import config from "@/main";

export async function getUserByUsername(username) {
     return axios.get(config.serviceUrl + '/api/users/info?username=' + username)
         .then(r => {
              return r.data
         }).catch(() => null)
}

export async function sendUserInvite(userId, username) {
    return {
        username: username,
        createdOn: Date.now()
    }
}

export async function acceptUserFriendRequest(userId, friendId) {
}

export async function declineUserFriendRequest(userId, friendId) {
}

export async function cancelUserFriendInvitation(userId, friendId) {
}

export async function deleteUserFriend(userId, friendId) {
}

export async function deleteUserTodoFriendAccess(userId, todoId, friendId) {
}

export async function allowUserTodoFriendAccess(userId, todoId, friendId, readOnly) {
}

export async function changeUserTodoFriendAccess(userId, todoId, friendId, readOnly) {
}

export async function getUserTodoFriendsAccess(userId, todoId, friendId) {
    return [
        {
            id: 2,
            username: "snape",
            readOnly: true,
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 3,
            username: "dobby",
            readOnly: true,
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 4,
            username: "belatrice",
            readOnly: false,
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 5,
            username: "sirius",
            readOnly: false,
            createdOn: 1676378789324,
            updatedOn: null
        },
    ]
}

export async function getUserFriendsRequests(userId) {
    return [
        {
            id: 2,
            username: "cherry",
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 3,
            username: "mickey",
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 4,
            username: "belatrice",
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 5,
            username: "sirius",
            createdOn: 1676378789324,
            updatedOn: null
        },
    ]
}

export async function getUserFriendsInvitations(userId) {
    return [
        {
            id: 2,
            username: "smith",
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 3,
            username: "neo",
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 4,
            username: "trinity",
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 5,
            username: "morpheus",
            createdOn: 1676378789324,
            updatedOn: null
        },
    ]
}

export async function getUserFriends(userId) {
    return [
        {
            id: 2,
            username: "trojan",
            firstName: "Test",
            lastName: "User",
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 3,
            username: "michael",
            firstName: "Test 1",
            lastName: "User 1",
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 4,
            username: "maleficent",
            firstName: "Test 2",
            lastName: "User 2",
            createdOn: 1676378789324,
            updatedOn: null
        },
        {
            id: 5,
            username: "carl",
            firstName: "Test 3",
            lastName: "User 3",
            createdOn: 1676378789324,
            updatedOn: null
        },
    ]
}