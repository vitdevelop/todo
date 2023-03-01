<template>
  <MDBModal :model-value="enabled">
    <MDBModalHeader>
      <MDBModalTitle>{{ todo.name }}</MDBModalTitle>
    </MDBModalHeader>
    <MDBModalBody>
      <span style="white-space: pre-line">
        {{ todo.description }}
      </span>
    </MDBModalBody>
    <MDBModalFooter>
      <MDBBtn color="secondary" @click="close()">Close</MDBBtn>
    </MDBModalFooter>
  </MDBModal>
</template>

<script setup>
import {MDBModal, MDBBtn, MDBModalHeader, MDBModalTitle, MDBModalBody, MDBModalFooter} from "mdb-vue-ui-kit";

defineProps(['todo', 'enabled']);
const emit = defineEmits(['close'])

function close() {
  emit('close', true)
}
</script>

<style scoped>

</style>