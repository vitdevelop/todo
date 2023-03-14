<template>
  <MDBModal :model-value="enabled">
    <MDBModalHeader :close="false">
      <MDBModalTitle>{{ todo.name }}</MDBModalTitle>
    </MDBModalHeader>
    <MDBModalBody>
      <MDBTabs v-model="activeTab">
        <!-- Tabs navs -->
        <MDBTabNav tabsClasses="mb-3">
          <MDBTabItem tabId="description">Description</MDBTabItem>
          <MDBTabItem tabId="allow_list">Allowed friends</MDBTabItem>
        </MDBTabNav>
        <!-- Tabs navs -->
        <!-- Tabs content -->
        <MDBTabContent>
          <MDBTabPane tabId="description">
            <span style="white-space: pre-line">
              {{ todo.description }}
            </span>
          </MDBTabPane>
          <MDBTabPane tabId="allow_list">
            <TodoAllowedFriends v-model:todo-id="todo.id"/>
          </MDBTabPane>
        </MDBTabContent>
        <!-- Tabs content -->
      </MDBTabs>
    </MDBModalBody>
    <MDBModalFooter>
      <MDBBtn color="secondary" @click="close()">Close</MDBBtn>
    </MDBModalFooter>
  </MDBModal>
</template>

<script setup>
import {
  MDBTabs,
  MDBTabContent,
  MDBTabPane,
  MDBTabNav,
  MDBTabItem,
  MDBModal,
  MDBBtn,
  MDBModalHeader,
  MDBModalTitle,
  MDBModalBody,
  MDBModalFooter
} from "mdb-vue-ui-kit";
import {ref} from "vue";
import TodoAllowedFriends from "@/components/TodoAllowedFriends.vue";

defineProps(['todo', 'enabled']);
const emit = defineEmits(['close'])

const activeTab = ref('description');

function close() {
  emit('close', true)
}
</script>

<style scoped>

</style>