<template>
  <MDBModal :model-value="enabled" tag="form" novalidate @submit.prevent="checkForm">
    <MDBModalHeader :close="false">
      <MDBModalTitle>New Todo</MDBModalTitle>
    </MDBModalHeader>
    <MDBModalBody>
      <MDBInput label="Title of todo" size="lg" class="mb-3" v-model="todo.name"
                invalidFeedback="Todo without title"
                validFeedback="Looks good!"
                required/>
      <MDBTextarea label="Details" rows="4" class="mt-4" v-model="todo.description"/>
    </MDBModalBody>
    <MDBModalFooter>
      <MDBBtn color="primary" type="submit" @click="addTodo">Save</MDBBtn>
      <MDBBtn color="secondary" @click="cancelAdd">Close</MDBBtn>
    </MDBModalFooter>
  </MDBModal>
</template>

<script setup>
import {MDBModal, MDBBtn, MDBModalHeader, MDBModalTitle, MDBModalBody, MDBModalFooter, MDBInput, MDBTextarea} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {currentUser} from "@/auth";
import {createUserTodo} from "@/api/todo";

defineProps(['enabled']);
const emit = defineEmits(['response']);

const todo = ref({
  name: '',
  description: ''
});

function cancelAdd() {
  clearTodo()
  emit('response', todo)
}

const addTodo = () => {
  if (todo.value.name === '') {
    return
  }
  const user = currentUser()
  createUserTodo(user.id, todo.value)
      .then(createdTodo => {
        emit('response', createdTodo);
        clearTodo()
      })
};

const checkForm = (event) => {
  event.target.classList.add("was-validated");
};

function clearTodo() {
  todo.value.name = ''
  todo.value.description = ''
}
</script>

<style scoped>

</style>