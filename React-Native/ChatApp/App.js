import React from 'react';
import { Router, Stack, Scene } from 'react-native-router-flux';
import { Home, Chat } from './components';

export default class App extends React.Component {
  render() {
    return (
      <Router>
        <Stack key="root">
          <Scene key="home" component={Home} title="Home Page" />
          <Scene key="chat" component={Chat} title="Chat" />
        </Stack>
      </Router>
    );
  }
}
