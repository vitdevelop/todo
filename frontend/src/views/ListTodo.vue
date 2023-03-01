<template>
  <div class="container">
    <h2 class="mb-3">Todo List</h2>

    <div class="row">
      <MDBBtn class="col-1" color="success" @click="newModal()">
        <MDBIcon icon="plus"/>
        Add
      </MDBBtn>

      <div class="col"/>

      <MDBBadge class="col-1 rounded-9 d-flex justify-content-center align-items-center" color="primary">
        <span>
            <MDBIcon icon="user-alt"/>
         {{ user.username }}
        </span>
      </MDBBadge>

      <MDBBtn class="col-1" color="danger" size="sm" rounded @click="logout()">
        <MDBIcon icon="sign-out-alt"/>
        Logout
      </MDBBtn>
    </div>

    <hr class="hr"/>

    <MDBTable class="align-middle mb-0 bg-light">
      <thead>
      <tr>
        <th>Name</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(todo, index) in todos" :key="index">
        <td @click="showModal(todo)">{{ todo.name }}</td>
        <td @click="showModal(todo)">{{ getPrettyDateFromTimeStamp(todo.createdOn) }}</td>
        <td @click="showModal(todo)">{{ getPrettyDateFromTimeStamp(todo.updatedOn) }}</td>
        <td>
          <MDBBtn color="link" size="sm" rounded @click="editModal(index, todoObject(todo))">
            <MDBIcon icon="edit"/>
            Edit
          </MDBBtn>

          <MDBBtn color="danger" size="sm" rounded @click="deleteTodosIndex(index)">
            <MDBIcon icon="trash-alt"/>
            Delete
          </MDBBtn>
        </td>
      </tr>
      </tbody>
    </MDBTable>

    <InfoTodoModal v-model:todo="todoModalData"
                   v-model:enabled="showInfoTodoModal"
                   @close="closeInfoModal()"/>

    <AddTodoModal v-model:enabled="showAddTodoModal"
                  @response="(todo) => handleNewTodoModal(todo)"/>

    <EditTodoModal v-model:todo-data="todoEditModalData"
                   v-model:enabled="showEditTodoModal"
                   @response="(todoData) => handleEditTodoModal(todoData.index, todoData.updatedTodo)"/>
  </div>
</template>

<script setup>
import {MDBTable, MDBBtn, MDBIcon, MDBBadge} from "mdb-vue-ui-kit";
import {ref} from "vue";
import InfoTodoModal from "@/components/InfoTodoModal.vue";
import AddTodoModal from "@/components/AddTodoModal.vue";
import EditTodoModal from "@/components/EditTodoModal.vue";
import {currentUser, logout} from "@/auth";
import {deleteUserTodo, getUserTodoById, getUserTodos} from "@/api/todo";

const showInfoTodoModal = ref(false);
const todoModalData = ref();

const showAddTodoModal = ref(false);
const showEditTodoModal = ref(false);
const todoEditModalData = ref({
  index: -1,
  todo: {}
});

const user = ref(currentUser())
let todos = ref([]);

getUserTodos(user.value.id)
    .then(userTodo => todos.value = userTodo)

function getPrettyDateFromTimeStamp(timestamp) {
  let date = new Date(timestamp);

  return date.toLocaleString();
}

function showModal(todo) {
  getUserTodoById(user.value.id, todo.id)
      .then(freshTodo => {
        showInfoTodoModal.value = true;
        todoModalData.value = freshTodo;
      })
}

function closeInfoModal() {
  showInfoTodoModal.value = false;
}

function newModal() {
  showAddTodoModal.value = true;
}

function editModal(index, todo) {
  todoEditModalData.value = {
    index: index,
    todo: todo
  }
  showEditTodoModal.value = true;
}

function handleNewTodoModal(newTodoEvent) {
  showAddTodoModal.value = false

  let todo = todoObject(newTodoEvent)
  if (todo.name === '') {
    return
  }

  todos.value.push(todo)
}

function handleEditTodoModal(index, editTodoEvent) {
  showEditTodoModal.value = false

  let todo = todoObject(editTodoEvent)
  if (todo.name === '') {
    return
  }

  todos.value[index] = todo
}

function deleteTodosIndex(index) {
  const todo = todos.value[index]

  deleteUserTodo(user.value.id, todo.id)
      .then(() => {
        todos.value.splice(index, 1)
      })
}

function todoObject(todoRef) {
  return {
    id: todoRef.id,
    name: todoRef.name,
    description: todoRef.description,
    createdOn: todoRef.createdOn,
    updatedOn: todoRef.updatedOn
  }
}

</script>

<style scoped>

</style>