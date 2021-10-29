import Vue from 'vue'

const EventBus = new Vue()

export default {
  EventBus,
  toast(message) {
    this.EventBus.$emit('toast', message);
  },
  startLoading() {
    this.EventBus.$emit('setLoadingState', true);
  },
  finishLoading() {
    this.EventBus.$emit('setLoadingState', false);
  }
}