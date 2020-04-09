import React from 'react';
import { Home, Chat } from './components'; // Utiliser les accolades quand il n'y a qu'un sur les 2 qui utilise `default`
import { Router, Stack, Scene } from 'react-native-router-flux';
import { StyleSheet } from 'react-native';

// export function App() { // Version avec fonction
export default class App extends React.Component { // Version avec classe
  render() {
    return (
      <Router navigationBarStyle={styles.appBarColor}>
        <Stack key="root">
          <Scene key="home" component={Home} title="Home" />
          <Scene key="chat" component={Chat} />
        </Stack>
      </Router>
    );
  }
}

const styles = StyleSheet.create({
  appBarColor: {
    backgroundColor: 'slategray'
  },
});
