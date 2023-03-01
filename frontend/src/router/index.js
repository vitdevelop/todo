import {createRouter, createWebHashHistory} from "vue-router";
import Login from "@/views/Login.vue";
import ListTodo from "@/views/ListTodo.vue";

const routes = [
    {path: '/', component: Login},
    {path: '/todo', component: ListTodo},
]
const router = createRouter({
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
})

export default router