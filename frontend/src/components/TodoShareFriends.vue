<template>
  <MDBTable class="mb-0 bg-light">
    <thead>
    <tr>
      <th>Username</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(friend, index) in todoFriendsAccess" :key="index">
      <td>{{ friend.username }}</td>
      <td>

        <MDBContainer>
          <MDBRow>
            <MDBCol>
              <MDBBtn v-if="!friend.haveAccess" color="success" @click="allowTodoFriendAccess(index)">Allow</MDBBtn>
            </MDBCol>
            <MDBCol>
              <MDBSwitch
                  label="Editable"
                  v-model="friend.editable"
                  @click="changeTodoFriendAccess(index)"/>
            </MDBCol>
          </MDBRow>
        </MDBContainer>

      </td>
    </tr>
    </tbody>
  </MDBTable>
</template>

<script setup>
import {MDBTable, MDBContainer, MDBRow, MDBCol, MDBSwitch, MDBBtn} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {currentUser} from "@/auth";
import {
  allowUserTodoFriendAccess, changeUserTodoFriendAccess,
  getUserFriends,
} from "@/api/user";

const props = defineProps(['todoId'])

const currentTodoId = props.todoId
const todoFriendsAccess = ref([])

const user = ref(currentUser())

getUserFriends(user.value.id,)
    .then(userFriends => {
      for (let friend of userFriends) {
        friend.haveAccess = false
        friend.readOnly = true

        todoFriendsAccess.value.push(toFriendAccess(friend))
      }
    })

function allowTodoFriendAccess(index) {
  const friend = todoFriendsAccess.value[index]
  friend.haveAccess = true

  allowUserTodoFriendAccess(user.value.id, currentTodoId, friend.id, !friend.editable)
      .then(() => {
      })
}

function changeTodoFriendAccess(index) {
  const friend = todoFriendsAccess.value[index]

  if (!friend.haveAccess) {
    return
  }

  changeUserTodoFriendAccess(user.value.id, currentTodoId, friend.id, !friend.editable)
      .then(() => {
      })
}

function toFriendAccess(friend) {
  return {
    id: friend.id,
    username: friend.username,
    haveAccess: friend.haveAccess,
    editable: !friend.readOnly,
  }
}

</script>

<style scoped>

</style>