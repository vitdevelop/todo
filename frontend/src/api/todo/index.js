import axios from "axios";
import config from "@/main";

export function getUserTodos(userId) {
    return axios.get(config.serviceUrl + '/api/users/' + userId + '/todo')
        .then(r => {
            return r.data
        }).catch(() => {
        })
}

export function getUserTodoById(userId, todoId) {
    return axios.get(config.serviceUrl + '/api/users/' + userId + '/todo/' + todoId)
        .then(r => {
            return r.data
        }).catch(() => {
        })
}

export function createUserTodo(userId, todo) {
    return axios.post(config.serviceUrl + '/api/users/' + userId + '/todo',
        todo)
        .then(r => {
            return r.data
        }).catch(() => {
        })
}

export function updateUserTodo(userId, todo) {
    return axios.put(config.serviceUrl + '/api/users/' + userId + '/todo/' + todo.id,
        todo)
        .then(r => {
            return r.data
        }).catch(() => {
        })
}

export function deleteUserTodo(userId, todoId) {
    return axios.delete(config.serviceUrl + '/api/users/' + userId + '/todo/' + todoId)
        .then(() => {
            return null
        }).catch(() => {
        })
}