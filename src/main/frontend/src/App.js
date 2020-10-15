import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends  Component{
  state = {
    isLoading: true,
    assets: []
  };
   async componentDidMount() {
     const response = await fetch('/api')
     const body = await  response.json()
     this.setState({assets: body, isLoading: false})
   }

   render() {
     const {assets, isLoading} = this.state;
     if (isLoading === true) {
       return  <p> Loading.. </p>
     }
     return (
         <div className="App">
           <header className="App-header">
             <img src={logo} className="App-logo" alt="logo" />
             <div className="App-intro">
               <h2> Networth Calculator</h2>
                 {assets.map( asset =>
                     <div key = {asset.id}>
                         {asset.name}
                     </div>
                 )}
             </div>
           </header>
         </div>


     )

   }
}



export default App;
