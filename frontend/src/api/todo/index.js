import axios from "axios";
import config from "@/main";

export async function getUserFriendsTodos(userId) {
    return [
        {
            id: 1,
            name: "my workbook",
            description: "some description",
            userId: 2,
            readOnly: true,
            createdOn: 1668517521168,
            updatedOn: 1677683053682
        },
        {
            id: 2,
            name: "amazing idea",
            description: "some edited description",
            userId: 2,
            readOnly: false,
            createdOn: 1668517521168,
            updatedOn: 1677684683285
        },
        {
            id: 12,
            name: "just ignore that",
            description: "simple updated description",
            userId: 2,
            readOnly: true,
            createdOn: 1677684902471,
            updatedOn: 1677684910842
        },
        {
            id: 13,
            name: "what is that?",
            description: "asdasdasd",
            userId: 2,
            readOnly: false,
            createdOn: 1678823042972,
            updatedOn: 1678823042972
        }
    ]
}

export async function getUserTodos(userId) {
    return axios.get(config.serviceUrl + '/api/users/' + userId + '/todo')
        .then(r => {
            return r.data
        }).catch(() => {
        })
}

export async function getUserTodoById(userId, todoId) {
    return axios.get(config.serviceUrl + '/api/users/' + userId + '/todo/' + todoId)
        .then(r => {
            return r.data
        }).catch(() => {
        })
}

export async function createUserTodo(userId, todo) {
    return axios.post(config.serviceUrl + '/api/users/' + userId + '/todo',
        todo)
        .then(r => {
            return r.data
        }).catch(() => {
        })
}

export async function updateUserTodo(userId, todo) {
    return axios.put(config.serviceUrl + '/api/users/' + userId + '/todo/' + todo.id,
        todo)
        .then(r => {
            return r.data
        }).catch(() => {
        })
}

export async function deleteUserTodo(userId, todoId) {
    return axios.delete(config.serviceUrl + '/api/users/' + userId + '/todo/' + todoId)
        .then(() => {
            return null
        }).catch(() => {
        })
}