<template>
  <MDBModal :model-value="enabled">
    <MDBModalHeader :close="false">
      <MDBModalTitle>{{ todo.name }}</MDBModalTitle>
    </MDBModalHeader>
    <MDBModalBody>
      <TodoShareFriends v-model:todoId="todo.id"/>
    </MDBModalBody>
    <MDBModalFooter>
      <MDBBtn color="secondary" @click="close()">Close</MDBBtn>
    </MDBModalFooter>
  </MDBModal>
</template>

<script setup>
import {MDBModal, MDBBtn, MDBModalHeader, MDBModalTitle, MDBModalBody, MDBModalFooter} from "mdb-vue-ui-kit";
import TodoShareFriends from "@/components/TodoShareFriends.vue";

const props = defineProps(['todo', 'enabled']);
const emit = defineEmits(['close'])

function close() {
  emit('close', true)
}
</script>

<style scoped>

</style>