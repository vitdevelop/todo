import {createRouter, createWebHashHistory} from "vue-router";
import Login from "@/views/Login.vue";
import FriendsView from "@/views/friends/FriendsView.vue";
import TodoView from "@/views/todo/TodoView.vue";

const routes = [
    {path: '/', component: Login},
    {path: '/todo', component: TodoView},
    {path: '/friends', component: FriendsView},
]
const router = createRouter({
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
})

export default router