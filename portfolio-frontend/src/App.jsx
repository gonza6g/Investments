import { useState } from 'react';
import ViewToggle from './components/ViewToggle';
import PortfolioContent from './components/PortfolioContent';
import { usePortfolioData } from './hooks/usePortfolioData';
import LastUpdate from './components/LastUpdate';
import './App.css';

function App() {
  const [view, setView] = useState('portfolio');
  const { portfolio, error, loading, totals, lastUpdateTime } = usePortfolioData();

  if (loading) return <div className="loading">Loading...</div>;
  if (error) return <div className="error">Error: {error}</div>;
  if (!portfolio) return <div className="no-data">No data found</div>;

  return (
    <div className="container">
      <div className="header">
        <h1>Investment Portfolio</h1>
        <div className="header-right">
          <LastUpdate timestamp={lastUpdateTime} />
          <ViewToggle 
            currentView={view} 
            onViewChange={setView} 
          />
        </div>
      </div>
      <PortfolioContent 
        view={view} 
        portfolio={portfolio} 
        totals={totals} 
      />
    </div>
  );
}

export default App;
