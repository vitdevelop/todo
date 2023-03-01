<template>
  <div class="container">
    <div class="row">
      <div class="col"></div>

      <MDBCol id="loginForm" col="2"
              class="bg-info bg-opacity-10 rounded-6 position-absolute top-50 start-50 translate-middle"
              tag="form" novalidate @submit.prevent="() => {}">
        <div class="container text-center mt-3">
          <h3>
            <MDBIcon icon="pencil-alt"/>
            Todo
          </h3>
        </div>
        <div class="mb-3">
          <MDBInput type="email" class="form-control" placeholder="Username"
                    v-model="username"
                    invalidFeedback="Please provide username"
                    validFeedback="Looks good!"
                    required/>
        </div>
        <div class="d-grid mt-4 mb-3">
          <MDBBtn type="submit" class="btn btn-primary" @click="auth()">Enter</MDBBtn>
        </div>
      </MDBCol>

      <div class="col"></div>
    </div>
  </div>
</template>

<script setup>
import {MDBIcon, MDBInput, MDBCol, MDBBtn} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {currentUser, login} from "@/auth";
import {getUserByUsername} from "@/api/user";

const username = ref('')
const user = ref(currentUser())

if (user.value !== null) {
  login(user.value)
}

function auth() {
  document.getElementById('loginForm').classList.add('was-validated')
  if (username.value === '') {
    return
  }

  getUserByUsername(username.value)
      .then(user => {
        if (user != null) {
          document.getElementById('loginForm').classList.remove('was-validated')
          login(user)
        }
      })
}

</script>

<style scoped>

</style>