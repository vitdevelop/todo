<template>
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
        <MDBBtn color="link" size="sm" rounded @click="shareTodoModal(todoObject(todo))">
          <MDBIcon icon="share-alt"/>
          Share
        </MDBBtn>

        <MDBBtn color="danger" size="sm" rounded @click="deleteTodosIndex(index)">
          <MDBIcon icon="trash-alt"/>
          Delete
        </MDBBtn>
      </td>
    </tr>
    </tbody>
  </MDBTable>

  <DetailedInfoTodoModal v-model:todo="todoModalData"
                         v-model:enabled="showInfoTodoModal"
                         @close="closeInfoModal()"/>

  <EditTodoModal v-model:todo-data="todoEditModalData"
                 v-model:enabled="showEditTodoModal"
                 @response="(todoData) => handleEditTodoModal(todoData.index, todoData.updatedTodo)"/>

  <ShareTodoModal
      v-model:todo="shareTodo"
      v-model:enabled="showShareTodoModal"
      @close="closeShareTodoModal()"/>
</template>

<script setup>
import {MDBTable, MDBBtn, MDBIcon} from "mdb-vue-ui-kit";
import {computed, ref} from "vue";
import EditTodoModal from "@/components/modals/EditTodoModal.vue";
import {currentUser} from "@/auth";
import {deleteUserTodo, getUserTodoById} from "@/api/todo";
import {getPrettyDateFromTimeStamp} from "@/util/date";
import {todoObject} from "@/util/todo";
import DetailedInfoTodoModal from "@/components/modals/DetailedInfoTodoModal.vue";
import ShareTodoModal from "@/components/modals/ShareTodoModal.vue";

const props = defineProps(['todos'])
const emit = defineEmits(['update:todos'])

const todos = computed({
  get() {
    return props.todos
  },
  set(value) {
    emit('update:todos', value)
  }
})

const showShareTodoModal = ref(false);
let shareTodo = ref([])

const showInfoTodoModal = ref(false);
const todoModalData = ref();

const showEditTodoModal = ref(false);
const todoEditModalData = ref({
  index: -1,
  todo: {}
});

const user = ref(currentUser())

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

function shareTodoModal(todo) {
  showShareTodoModal.value = true
  shareTodo.value = todo
}

function closeShareTodoModal() {
  showShareTodoModal.value = false
}

function editModal(index, todo) {
  todoEditModalData.value = {
    index: index,
    todo: todo
  }
  showEditTodoModal.value = true;
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

</script>

<style scoped>

</style>