import React from 'react';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'react-redux'
import { Provider } from 'react-redux';
import rootReducer from './reducers';
import { StyleSheet } from 'react-native';
import { Router, Stack, Scene } from 'react-native-router-flux';

import { Home, Chat } from './components';

const store = createStore(rootReducer, applyMiddleware(thunk));

export default class App extends React.Component {
  render() {
    return (
      <Provider store={store}>
        <Router navigationBarStyle={styles.navigationBarStyle} titleStyle={{ color: 'white' }}>
          <Stack key="root">
            <Scene key="home" component={Home} title="Home" />
            <Scene key="chat" component={Chat} title="Chat" />
          </Stack>
        </Router>
      </Provider>
    );
  }
}

const styles = StyleSheet.create({
  navigationBarStyle: {
    backgroundColor: 'darkcyan',
  },
  titleStyle: {
    color: 'white',
  }
})