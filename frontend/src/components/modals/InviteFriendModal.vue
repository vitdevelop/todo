<template>
  <MDBModal :model-value="enabled" tag="form" novalidate @submit.prevent="checkForm">
    <MDBModalHeader :close="false">
      <MDBModalTitle>Invite Friend</MDBModalTitle>
    </MDBModalHeader>
    <MDBModalBody>
      <MDBInput label="Friend username" size="lg" class="mb-3" v-model="user.username"
                invalidFeedback="Need username"
                validFeedback="Looks good!"
                required/>
    </MDBModalBody>
    <MDBModalFooter>
      <MDBBtn color="success" type="submit" @click="inviteUser()">Invite</MDBBtn>
      <MDBBtn color="secondary" @click="cancelInvite()">Close</MDBBtn>
    </MDBModalFooter>
  </MDBModal>
</template>

<script setup>
import {MDBModal, MDBBtn, MDBModalHeader, MDBModalTitle, MDBModalBody, MDBModalFooter, MDBInput} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {currentUser} from "@/auth";
import {sendUserInvite} from "@/api/user";

defineProps(['enabled']);
const emit = defineEmits(['response']);

const user = ref({
  username: '',
});

function cancelInvite() {
  clearUser()
  emit('response', user)
}

function inviteUser() {
  if (user.value.username === '') {
    return
  }

  const loggedUser = currentUser()
  sendUserInvite(loggedUser.id, user.value.username)
      .then(invitedUser => {
        emit('response', invitedUser);
        clearUser()
      })
}

const checkForm = (event) => {
  event.target.classList.add("was-validated");
};

function clearUser() {
  user.value.username = ''
}
</script>

<style scoped>

</style>