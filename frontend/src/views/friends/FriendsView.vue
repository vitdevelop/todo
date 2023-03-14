<template>
  <div class="container">
    <h2 class="mb-3">Friends</h2>

    <div class="row">
      <MDBBtn class="col-1" color="success" @click="inviteFriendModal()">
        <MDBIcon icon="plus"/>
        Invite
      </MDBBtn>

      <div class="col"/>

      <UserBadge
          class="col-2 rounded-9 d-flex justify-content-center align-items-center"
          color="primary"/>

    </div>

    <hr class="hr"/>
    <MDBTabs v-model="activeTab">
      <!-- Tabs navs -->
      <MDBTabNav tabsClasses="mb-3">
        <MDBTabItem tabId="connections">Connections</MDBTabItem>
        <MDBTabItem tabId="invitations">Invitations</MDBTabItem>
        <MDBTabItem tabId="requests">Requests</MDBTabItem>
      </MDBTabNav>
      <!-- Tabs navs -->
      <!-- Tabs content -->
      <MDBTabContent>
        <MDBTabPane tabId="connections">
          <ListFriends v-model:friendsList="friends"/>
        </MDBTabPane>
        <MDBTabPane tabId="invitations">
          <ListInvitations v-model:friendsInvitations="invitations"/>
        </MDBTabPane>
        <MDBTabPane tabId="requests">
          <ListRequests v-model:requestsList="requests" @acceptInvitation="(newFriend) => addFriend(newFriend)"/>
        </MDBTabPane>
      </MDBTabContent>
      <!-- Tabs content -->
    </MDBTabs>

    <InviteFriendModal v-model:enabled="showInviteFriendModal"
                       @response="(userResponse) => handleInviteFriendModal(userResponse)"/>
  </div>
</template>

<script setup>
import {MDBTabs, MDBTabContent, MDBTabPane, MDBTabNav, MDBTabItem, MDBBtn, MDBIcon} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {currentUser} from "@/auth";
import UserBadge from "@/components/UserBadge.vue";
import {getUserFriends} from "@/api/user";
import InviteFriendModal from "@/components/modals/InviteFriendModal.vue";
import ListFriends from "@/views/friends/ListFriends.vue";
import ListInvitations from "@/views/friends/ListInvitations.vue";
import ListRequests from "@/views/friends/ListRequests.vue";
import {userObject} from "@/util/user";

const activeTab = ref('connections');

const showInviteFriendModal = ref(false);

const user = ref(currentUser())
let friends = ref([]);
let invitations = ref([]);
let requests = ref([]);

getUserFriends(user.value.id)
    .then(userFriends => friends.value = userFriends)

function inviteFriendModal() {
  showInviteFriendModal.value = true;
}

function addFriend(friend) {
  friends.value.push(friend)
}

function handleInviteFriendModal(inviteFriendEvent) {
  showInviteFriendModal.value = false

  let friend = userObject(inviteFriendEvent)
  if (!friend.username || friend.username === '') {
    return
  }

  invitations.value.push(friend)
}

</script>

<style scoped>

</style>