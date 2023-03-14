<template>
    <MDBTable class="mb-0 bg-light">
      <thead>
      <tr>
        <th>Username</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(friend, index) in friends" :key="index">
        <td>{{ friend.username }}</td>
        <td>{{ friend.firstName }}</td>
        <td>{{ friend.lastName }}</td>
        <td>

          <MDBBtn color="danger" size="sm" rounded @click="deleteFriend(index)">
            <MDBIcon icon="times-circle"/>
            Revoke
          </MDBBtn>
        </td>
      </tr>
      </tbody>
    </MDBTable>
</template>

<script setup>
import {MDBTable, MDBBtn, MDBIcon} from "mdb-vue-ui-kit";
import {computed, ref} from "vue";
import {currentUser} from "@/auth";
import {deleteUserFriend, getUserFriends} from "@/api/user";

const props = defineProps(['friendsList'])
const emit = defineEmits(['update:friendsList'])

const friends = computed({
  get() {
    return props.friendsList
  },
  set(value) {
    emit('update:friendsList', value)
  }
})

const user = ref(currentUser())

getUserFriends(user.value.id)
    .then(userFriends => friends.value = userFriends)


function deleteFriend(index) {
  const friend = friends.value[index]

  deleteUserFriend(user.value.id, friend.id)
      .then(() => {
        friends.value.splice(index, 1)
      })
}

</script>

<style scoped>

</style>