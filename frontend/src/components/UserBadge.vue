<template>
  <MDBDropdown v-model="userDropdownProfile">
    <MDBBadge class="rounded-9">
      <MDBDropdownToggle color="info" class="rounded-9"
          @click="userDropdownProfile = !userDropdownProfile">
        {{ user.username }}
      </MDBDropdownToggle>
    </MDBBadge>
    <MDBDropdownMenu>
      <MDBDropdownItem tag="button" @click="routeTodo()">
        <MDBIcon icon="list-alt"/>
        Todos
      </MDBDropdownItem>
      <MDBDropdownItem tag="button" @click="routeFriends()">
        <MDBIcon icon="user-friends"/>
        Friends
      </MDBDropdownItem>
      <MDBDropdownItem divider/>
      <MDBDropdownItem tag="button" class="badge-danger" @click="logout()">
        <MDBIcon icon="sign-out-alt"/>
        Logout
      </MDBDropdownItem>
    </MDBDropdownMenu>
  </MDBDropdown>
</template>

<script setup>
import {MDBBadge, MDBIcon, MDBDropdown, MDBDropdownToggle, MDBDropdownItem, MDBDropdownMenu} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {currentUser, logout} from "@/auth";
import router from "@/router";

const userDropdownProfile = ref(false)
const user = ref(currentUser())

function routeTodo() {
  router.push('/todo').then(() => {
  })
}

function routeFriends() {
  router.push('/friends').then(() => {
  })
}

</script>

<style scoped>

</style>