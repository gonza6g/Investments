import { useState, useEffect } from 'react';
import PortfolioView from './components/PortfolioView';
import GainsView from './components/GainsView';
import './App.css';

function App() {
  const [portfolio, setPortfolio] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [view, setView] = useState('portfolio');

  useEffect(() => {
    console.log('Fetching portfolio data...')
    fetch('http://localhost:8080/api/portfolio', {
      headers: {
        'Accept': 'application/json',
        'Accept-Charset': 'utf-8'
      }
    })
      .then(response => {
        console.log('Response status:', response.status)
        if (!response.ok) {
          throw new Error(`Network response was not ok: ${response.status}`)
        }
        return response.json()
      })
      .then(data => {
        console.log('Raw API response:', JSON.stringify(data, null, 2));
        const portfolioData = data.activos.map(asset => {
          console.log('Raw asset data:', JSON.stringify(asset, null, 2));
          return {
            name: asset.titulo.descripcion?.normalize('NFD')
              .replace(/[\u0300-\u036f]/g, '') || '',
            symbol: asset.titulo.simbolo,
            price: asset.ppc || 0,
            holdings: asset.cantidad || 0,
            value: (asset.ppc * asset.cantidad) || 0,
            change24h: asset.puntosVariacion || 0,
            country: asset.titulo.pais || '-',
            industry: asset.titulo.industry || '-',
            gains: asset.ganancia || asset.gananciadinero || 0
          };
        });
        console.log('Mapped portfolio data:', JSON.stringify(portfolioData, null, 2));
        setPortfolio(portfolioData)
        setLoading(false)
      })
      .catch(error => {
        console.error('Fetch error:', error)
        setError(error.message)
        setLoading(false)
      })
  }, [])

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  if (!portfolio) return <div>No data found</div>;

  return (
    <div className="container">
      <div className="header">
        <h1>Investment Portfolio</h1>
        <div className="view-toggle">
          <button 
            className={`toggle-button ${view === 'portfolio' ? 'active' : ''}`}
            onClick={() => setView('portfolio')}
          >
            Portfolio View
          </button>
          <button 
            className={`toggle-button ${view === 'gains' ? 'active' : ''}`}
            onClick={() => setView('gains')}
          >
            Gains View
          </button>
        </div>
      </div>
      {view === 'portfolio' ? 
        <PortfolioView portfolio={portfolio} /> : 
        <GainsView portfolio={portfolio} />
      }
    </div>
  );
}

export default App;
