import { useState, useEffect } from 'react';

export function usePortfolioData() {
  const [portfolio, setPortfolio] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [totals, setTotals] = useState({ gains: 0, losses: 0 });
  const [lastUpdateTime, setLastUpdateTime] = useState(null);

  useEffect(() => {
    const fetchPortfolioData = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/portfolio', {
          headers: {
            'Accept': 'application/json',
            'Accept-Charset': 'utf-8'
          }
        });

        if (!response.ok) {
          throw new Error(`Network response was not ok: ${response.status}`);
        }

        const data = await response.json();
        
        const portfolioData = data.activos.map(asset => ({
          name: asset.titulo.descripcion?.normalize('NFD')
            .replace(/[\u0300-\u036f]/g, '') || '',
          symbol: asset.titulo.simbolo,
          price: asset.ppc || 0,
          holdings: asset.cantidad || 0,
          value: (asset.ppc * asset.cantidad) || 0,
          change24h: asset.puntosVariacion || 0,
          country: asset.titulo.pais || '-',
          industry: asset.titulo.industry || '-',
          gains: asset.ganancia || 0
        }));

        setPortfolio(portfolioData);
        setTotals({
          gains: data.totalGains || 0,
          losses: data.totalLosses || 0
        });
        setLastUpdateTime(data.lastUpdateTime);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchPortfolioData();
  }, []);

  return { portfolio, error, loading, totals, lastUpdateTime };
} 