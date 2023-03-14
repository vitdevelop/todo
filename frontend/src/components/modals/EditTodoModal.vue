<template>
  <MDBModal :model-value="enabled">
    <MDBModalHeader :close="false">
      <MDBModalTitle>New Todo</MDBModalTitle>
    </MDBModalHeader>
    <MDBModalBody>
      <MDBInput label="Form control lg" size="lg" class="mb-3" v-model="todoData.todo.name" />
      <MDBTextarea label="Message" rows="4" class="mb-3" v-model="todoData.todo.description"/>
    </MDBModalBody>
    <MDBModalFooter>
      <MDBBtn color="primary" @click="editTodo">Save</MDBBtn>
      <MDBBtn color="secondary" @click="cancelEdit">Close</MDBBtn>
    </MDBModalFooter>
  </MDBModal>
</template>

<script setup>
import {MDBModal, MDBBtn, MDBModalHeader, MDBModalTitle, MDBModalBody, MDBModalFooter, MDBInput, MDBTextarea} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {currentUser} from "@/auth";
import {updateUserTodo} from "@/api/todo";

const props = defineProps(['todoData', 'enabled']);
const emit = defineEmits(['response'])

const todo = ref({
  name: '',
  description: ''
});

function cancelEdit() {
  let index = props.todoData.index
  clearTodo()
  emit('response', {index, todo})
}

function editTodo() {
  todo.value = props.todoData.todo
  let index = props.todoData.index

  const user = currentUser()
  updateUserTodo(user.id, todo.value)
      .then(updatedTodo => {
        emit('response', {index, updatedTodo});
        clearTodo()
      })
}

function clearTodo() {
  todo.value.id = ''
  todo.value.name = ''
  todo.value.description = ''
  todo.value.createdOn = ''
  todo.value.updatedOn = ''
}
</script>

<style scoped>

</style>