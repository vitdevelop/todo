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
              <MDBSwitch
                  label="Editable"
                  v-model="friend.readOnly"/>
            </MDBCol>
            <MDBCol>
              <MDBBtn color="danger" size="sm" rounded @click="deleteTodoFriendAccess(index)">
                <MDBIcon icon="times-circle"/>
                Revoke
              </MDBBtn>
            </MDBCol>
          </MDBRow>
        </MDBContainer>

      </td>
    </tr>
    </tbody>
  </MDBTable>
</template>

<script setup>
import {MDBTable, MDBContainer, MDBRow, MDBCol, MDBSwitch, MDBBtn, MDBIcon} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {currentUser} from "@/auth";
import {deleteUserTodoFriendAccess, getUserTodoFriendsAccess} from "@/api/user";

const props = defineProps(['todoId'])

const currentTodoId = props.todoId
const todoFriendsAccess = ref([])

const user = ref(currentUser())

getUserTodoFriendsAccess(user.value.id, currentTodoId)
    .then(userFriends => todoFriendsAccess.value = userFriends)

function deleteTodoFriendAccess(index) {
  const friend = todoFriendsAccess.value[index]

  deleteUserTodoFriendAccess(user.value.id, currentTodoId, friend.id)
      .then(() => {
        todoFriendsAccess.value.splice(index, 1)
      })
}

</script>

<style scoped>

</style>