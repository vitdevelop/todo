<template>
  <div class="container">
    <h2 class="mb-3">Todo List</h2>

    <div class="row">
      <MDBBtn class="col-1" color="success" @click="newModal()">
        <MDBIcon icon="plus"/>
        Add
      </MDBBtn>

      <div class="col"/>

      <UserBadge
          class="col-2 rounded-9 d-flex justify-content-center align-items-center"
          color="primary"/>
    </div>

    <hr class="hr"/>

    <MDBTabs v-model="activeTab">
      <!-- Tabs navs -->
      <MDBTabNav tabsClasses="mb-3">
        <MDBTabItem tabId="myTodos">My Todos</MDBTabItem>
        <MDBTabItem tabId="sharedTodos">Shared Todos</MDBTabItem>
      </MDBTabNav>
      <!-- Tabs navs -->
      <!-- Tabs content -->
      <MDBTabContent>
        <MDBTabPane tabId="myTodos">
          <ListTodo v-model:todos="myTodos"/>
        </MDBTabPane>
        <MDBTabPane tabId="sharedTodos">
          <SharedTodo v-model:todos="sharedTodos"/>
        </MDBTabPane>
      </MDBTabContent>
      <!-- Tabs content -->
    </MDBTabs>

    <AddTodoModal v-model:enabled="showAddTodoModal"
                  @response="(todo) => handleNewTodoModal(todo)"/>
  </div>
</template>

<script setup>
import {MDBTabs, MDBTabContent, MDBTabPane, MDBTabNav, MDBTabItem, MDBBtn, MDBIcon} from "mdb-vue-ui-kit";
import {ref} from "vue";
import AddTodoModal from "@/components/modals/AddTodoModal.vue";
import {currentUser} from "@/auth";
import {getUserFriendsTodos, getUserTodos} from "@/api/todo";
import UserBadge from "@/components/UserBadge.vue";
import ListTodo from "@/views/todo/MyTodo.vue";
import {todoObject} from "@/util/todo";
import SharedTodo from "@/views/todo/SharedTodo.vue";

const activeTab = ref('myTodos');

const showAddTodoModal = ref(false);

const user = ref(currentUser())
let myTodos = ref([]);
let sharedTodos = ref([]);

getUserTodos(user.value.id)
    .then(userTodo => myTodos.value = userTodo)

getUserFriendsTodos(user.value.id)
    .then(friendsTodos => sharedTodos.value = friendsTodos)

function newModal() {
  showAddTodoModal.value = true;
}

function handleNewTodoModal(newTodoEvent) {
  showAddTodoModal.value = false

  let todo = todoObject(newTodoEvent)
  if (todo.name === '') {
    return
  }

  myTodos.value.push(todo)
}

</script>

<style scoped>

</style>