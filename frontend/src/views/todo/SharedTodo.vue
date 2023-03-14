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
          <MDBBtn v-if="!todo.readOnly" color="link" size="sm" rounded @click="editModal(index, todoObject(todo))">
            <MDBIcon icon="edit"/>
            Edit
          </MDBBtn>

          <h6 v-if="todo.readOnly">
            <MDBBadge color="light" class="text-dark">Read Only</MDBBadge>
          </h6>
        </td>
      </tr>
      </tbody>
    </MDBTable>

    <InfoTodoModal v-model:todo="todoModalData"
                   v-model:enabled="showInfoTodoModal"
                   @close="closeInfoModal()"/>

    <EditTodoModal v-model:todo-data="todoEditModalData"
                   v-model:enabled="showEditTodoModal"
                   @response="(todoData) => handleEditTodoModal(todoData.index, todoData.updatedTodo)"/>
</template>

<script setup>
import {MDBTable, MDBBtn, MDBIcon, MDBBadge} from "mdb-vue-ui-kit";
import {computed, ref} from "vue";
import InfoTodoModal from "@/components/modals/InfoTodoModal.vue";
import EditTodoModal from "@/components/modals/EditTodoModal.vue";
import {currentUser} from "@/auth";
import {deleteUserTodo, getUserTodoById, getUserTodos} from "@/api/todo";
import UserBadge from "@/components/UserBadge.vue";
import {getPrettyDateFromTimeStamp} from "@/util/date";
import {todoObject} from "@/util/todo";

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

</script>

<style scoped>

</style>