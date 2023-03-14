<template>
    <MDBTable class="mb-0 bg-light">
      <thead>
      <tr>
        <th>Username</th>
        <th>Sent date</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(friend, index) in invitations" :key="index">
        <td>{{ friend.username }}</td>
        <td>{{ getPrettyDateFromTimeStamp(friend.createdOn) }}</td>
        <td>

          <MDBBtn color="danger" size="sm" rounded @click="cancelInvitation(index)">
            <MDBIcon icon="times-circle"/>
            Cancel
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
import {getUserFriendsInvitations, cancelUserFriendInvitation} from "@/api/user";
import {getPrettyDateFromTimeStamp} from "@/util/date";

const props = defineProps(['friendsInvitations'])
const emit = defineEmits(['update:friendsInvitations'])

const invitations = computed({
  get() {
    return props.friendsInvitations
  },
  set(value) {
    emit('update:friendsInvitations', value)
  }
})

const user = ref(currentUser())

getUserFriendsInvitations(user.value.id)
    .then(userFriends => invitations.value = userFriends)


function cancelInvitation(index) {
  const friend = invitations.value[index]

  cancelUserFriendInvitation(user.value.id, friend.id)
      .then(() => {
        invitations.value.splice(index, 1)
      })
}

</script>

<style scoped>

</style>