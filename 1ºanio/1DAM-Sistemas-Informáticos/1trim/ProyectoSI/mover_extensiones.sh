#!/bin/bash
# Movemos según extensiones del PDF [cite: 29, 30, 31]

	# De cualquier sitio a Recursos (RAW, WAV)
find /opt/compartido -name "*.RAW" -o -name "*.WAV" -exec mv {} /opt/compartido/recursos/ \;

	# De cualquier sitio a Videos Finales (mp3, mp4, jpg, svg)
find /opt/compartido -name "*.mp3" -o -name "*.mp4" -o -name "*.jpg" -o -name "*.svg" -exec mv {} /opt/compartido/videos_finales/ \;

	# De cualquier sitio a Estadísticas (xls, doc, py, etc.)
find /opt/compartido -name "*.xls*" -o -name "*.doc*" -o -name "*.py" -o -name "*.csv" -o -name "*.json" -exec mv {} /opt/compartido/estadisticas/ \;