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
      <tr v-for="(friend, index) in requests" :key="index">
        <td>{{ friend.username }}</td>
        <td>{{ getPrettyDateFromTimeStamp(friend.createdOn) }}</td>
        <td>

          <MDBBtn color="success" size="sm" rounded @click="acceptInvitation(index)">
            <MDBIcon icon="check"/>
            Accept
          </MDBBtn>

          <MDBBtn color="danger" size="sm" rounded @click="declineInvitation(index)">
            <MDBIcon icon="times-circle"/>
            Decline
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
import {getUserFriendsRequests, declineUserFriendRequest, acceptUserFriendRequest} from "@/api/user";
import {getPrettyDateFromTimeStamp} from "@/util/date";

const props = defineProps(['requestsList'])
const emit = defineEmits(['update:requestsList', 'acceptInvitation'])

const requests = computed({
  get() {
    return props.requestsList
  },
  set(value) {
    emit('update:requestsList', value)
  }
})

const user = ref(currentUser())

getUserFriendsRequests(user.value.id)
    .then(userRequests => requests.value = userRequests)

function acceptInvitation(index) {
  const friend = requests.value[index]

  acceptUserFriendRequest(user.value.id, friend.id)
      .then(() => {
        requests.value.splice(index, 1)

        let acceptedFriend = friend
        acceptedFriend.firstName = 'Best'
        acceptedFriend.lastName = 'Friend'
        emit('acceptInvitation', acceptedFriend)
      })
}

function declineInvitation(index) {
  const friend = requests.value[index]

  declineUserFriendRequest(user.value.id, friend.id)
      .then(() => {
        requests.value.splice(index, 1)
      })
}

</script>

<style scoped>

</style>